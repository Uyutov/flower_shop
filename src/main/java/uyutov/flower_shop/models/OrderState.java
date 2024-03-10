package uyutov.flower_shop.models;

public class OrderState {
    private Integer state_id;
    private Integer order_id;
    private String state;

    public OrderState(Integer stateId, Integer orderId, String state) {
        state_id = stateId;
        order_id = orderId;
        this.state = state;
    }

    public OrderState() {
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
