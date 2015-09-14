package bean;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private ShoppingCart(){};
	private static ShoppingCart shoppingCart=new ShoppingCart();
	
	public static ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	private Integer LotteryID;
	private String issueString;
	private Integer lotteryNumber;
	private Integer lotteryValue;
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public Integer getLotteryID() {
		return LotteryID;
	}

	public void setLotteryID(Integer lotteryID) {
		LotteryID = lotteryID;
	}

	public String getIssueString() {
		return issueString;
	}

	public void setIssueString(String issueString) {
		this.issueString = issueString;
	}

	public Integer getLotteryNumber() {
		lotteryNumber = 0;
		for (Ticket item : tickets) {
			lotteryNumber += item.getNum();
		}
		return lotteryNumber;
	}

	public Integer getLotteryValue() {
		lotteryValue = 2 * getLotteryNumber();
		return lotteryValue;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}
}
