package domain;

import java.math.BigDecimal;
import java.util.Date;

public class Inventory {

	private Integer id;

	private Integer supplierId;

	private Integer itemId;

	private Integer price;

	private Integer quantity;

	private String memo;

	private Date created;

	private Date update;

	private String supplierName;

	private String itemName;

	private BigDecimal countQuantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	// debug
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", supplierId=" + supplierId + ", itemId=" + itemId + ", price=" + price
				+ ", quantity=" + quantity + ", created=" + created + ", supplierName=" + supplierName + ", itemName="
				+ itemName + "]";
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public BigDecimal getCountQuantity() {
		return countQuantity;
	}

	public void setCountQuantity(BigDecimal countQuantity) {
		this.countQuantity = countQuantity;
	}









}
