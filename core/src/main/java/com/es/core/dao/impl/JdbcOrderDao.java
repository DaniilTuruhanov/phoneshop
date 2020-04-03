package com.es.core.dao.impl;

import com.es.core.dao.OrderDao;
import com.es.core.dao.extractor.OrderExtractor;
import com.es.core.model.CartEntity;
import com.es.core.model.OrderModel;
import com.es.core.model.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcOrderDao implements OrderDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ORDER_WITH_ID_QUERY = "select * from phones p " +
            "left outer join phone2color p2с on p.id=p2с.phoneId " +
            "left outer join colors c on p2с.colorId=c.id " +
            "inner join order2phone o2p on p.id=o2p.phoneId " +
            "inner join orders o on o2p.orderId=o.id " +
            "inner join stocks s on p.id=s.phoneId " +
            "where o.id=?";

    private static final String GET_ORDER_WITH_NUMBER_QUERY = "select * from phones p " +
            "left outer join phone2color p2с on p.id=p2с.phoneId " +
            "left outer join colors c on p2с.colorId=c.id " +
            "inner join order2phone o2p on p.id=o2p.phoneId " +
            "inner join orders o on o2p.orderId=o.id " +
            "inner join stocks s on p.id=s.phoneId " +
            "where o.number=?";

    private static final String GET_ORDERS_QUERY = "select * from phones p " +
            "left outer join phone2color p2с on p.id=p2с.phoneId " +
            "left outer join colors c on p2с.colorId=c.id " +
            "inner join order2phone o2p on p.id=o2p.phoneId " +
            "inner join orders o on o2p.orderId=o.id " +
            "inner join stocks s on p.id=s.phoneId ";

    private static final String UPDATE_STATUS_QUERY = "update orders set status=? where number=?";

    private static final String INSERT_ORDER_QUERY = "insert into orders (firstName, lastName,address, id, data,phone, orderDescription, delivery,status) values (?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_INTO_ORDER2PHONE_QUERY = "insert into order2phone values (?,?,?)";

    private static final String UPDATE_STOCKS_QUERY = "update stocks set stock=? where phoneId=?";

    @Override
    public void saveOrder(OrderModel order) {
        jdbcTemplate.update(INSERT_ORDER_QUERY, order.getUserModel().getFirstName(), order.getUserModel().getLastName(), order.getUserModel().getAddress(), order.getId(), order.getUserModel().getDate(), order.getUserModel().getPhone(), order.getUserModel().getDescription(), order.getDeliveryCost(), order.getStatus().name());
        for (CartEntity cartEntity : order.getCartEntityList()) {
            jdbcTemplate.update(INSERT_INTO_ORDER2PHONE_QUERY, cartEntity.getPhone().getId(), order.getId(), cartEntity.getQuantity());
            jdbcTemplate.update(UPDATE_STOCKS_QUERY, cartEntity.getPhone().getStock() - cartEntity.getQuantity(), cartEntity.getPhone().getId());
        }
    }

    @Override
    public Optional<OrderModel> getOrder(String secureId) {
        List<OrderModel> orderList = jdbcTemplate.query(GET_ORDER_WITH_ID_QUERY, new OrderExtractor(), secureId);
        return orderList.stream().findAny();
    }

    @Override
    public List<OrderModel> getOrderList() {
        List<OrderModel> orderList = jdbcTemplate.query(GET_ORDERS_QUERY, new OrderExtractor());
        return orderList;
    }

    @Override
    public void changeStatus(Status status, Integer number) {
        jdbcTemplate.update(UPDATE_STATUS_QUERY, status.name(), number);
    }

    @Override
    public Optional<OrderModel> getOrder(Integer number) {
        List<OrderModel> orderList = jdbcTemplate.query(GET_ORDER_WITH_NUMBER_QUERY, new OrderExtractor(), number);
        return orderList.stream().findAny();
    }
}
