import java.time.LocalDate;

public class Review {

//	private LocalDate reviewDate = LocalDate.now();
	private String reviewDate;
	private String comment;
	private Integer rate;
	
	public Review(String date, String userComment, int userRate){
		this.reviewDate = date;
		this.comment = userComment;
		this.rate = userRate;
	}
	
	public String getReviewDate(){
		return this.reviewDate;
	}
	public String getComment(){
		return this.comment;
	}
	public Integer getRate(){
		return this.rate;
	}
	public String toString(){
		return
				"\nDate " + this.reviewDate +
				"\nComment " + this.comment + 
				"\nRate " + this.rate;
	}
}
