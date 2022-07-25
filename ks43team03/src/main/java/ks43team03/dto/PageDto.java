package ks43team03.dto;


public class PageDto {
	private int rowPerPage;
	
	private int startRow;
	
	private int lastPage;
	
	private double rowCount;
	
	private int startPage;
	
	private int endPage;
	
	private int currentPage;
	
	
	// 만약 회원아이디가 있을경우
	private String userId;
	
	public PageDto() {
		
	}
	
	
	
	public PageDto(double rowCount, int currentPage, int rowPerPage) {
		this.rowCount = rowCount;
		this.currentPage = currentPage;
		this.rowPerPage = rowPerPage;
		startRowAndLastPage(rowCount,currentPage,rowPerPage);
	}
	
	// 회원아이디가 있는 경우
	public PageDto(double rowCount, int currentPage, int rowPerPage,String userId) {
		this(rowCount,currentPage,rowPerPage);
		this.userId = userId;
	}
	
	private void startRowAndLastPage(double rowCount, int currentPage, int rowPerPage) {
		this.lastPage = (int)Math.ceil(rowCount / rowPerPage);
		this.startRow = (currentPage - 1) * rowPerPage;
		startAndEndPage();
	}
	
	private void startAndEndPage() {
		startPage = 1;
		endPage = 10;
		if(this.lastPage > 10) {
			if(this.currentPage >= 6) {
				this.startPage = this.currentPage - 4;
				this.endPage = this.currentPage + 5;
				if(this.endPage >= lastPage) {
					this.startPage = this.lastPage - 9;
					this.endPage = this.lastPage;
				}
			}
		}else {
			this.endPage = this.lastPage;
		}
	}
	
	
	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getLastPage() {
		return lastPage;
	}

	public double getRowCount() {
		return rowCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public String getUserId() {
		return userId;
	}
	
	
}
