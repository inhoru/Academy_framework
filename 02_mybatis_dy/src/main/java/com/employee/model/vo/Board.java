package com.employee.model.vo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Board {
	private int boardNo;
	private String boardTitle; 
	//private String boardWriter; 
	private String boardContent; 
	private String boardOriginalFilename; 
	private String boardRenamedFilename; 
	private Date boardDate; 
	private int boardReadcount; 
	private Member boardWriter;
	private List<BoardComment> boardComment;
	
	
	
	


}
