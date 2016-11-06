package studio.baxia.fo.vo;

public class AuthorSessionVo {
	private String account;
	private int id;

	public AuthorSessionVo(String account, int id) {
		super();
		this.account = account;
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AuthorSessionVo [account=" + account + ", id=" + id + "]";
	}

}
