package pojo_Orders;

public class OrderDetails {
	private String country;
	private String productOrderedId;
	private int orderQuantity;
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the productOrderedId
	 */
	public String getProductOrderedId() {
		return productOrderedId;
	}
	/**
	 * @param productOrderedId the productOrderedId to set
	 */
	public void setProductOrderedId(String productOrderedId) {
		this.productOrderedId = productOrderedId;
	}
	/**
	 * @return the orderQuantity
	 */
	public int getOrderQuantity() {
		return orderQuantity;
	}
	/**
	 * @param orderQuantity the orderQuantity to set
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
