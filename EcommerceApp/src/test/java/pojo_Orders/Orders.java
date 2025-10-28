package pojo_Orders;

import java.util.List;

public class Orders {
	private List<OrderDetails> orders;

	/**
	 * @return the orders
	 */
	public List<OrderDetails> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}

}
