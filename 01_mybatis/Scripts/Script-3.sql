SELECT * FROM (SELECT E.*, decode(SUBSTR(EMP_NO,8,1),'1','M','2','F','3','M','4','F')AS GENDER FROM EMPLOYEE E) WHERE GENDER='M'; 
	
	EMP_NAME LIKE '%이노%' OR SUBSTR(EMP_NO,8,1)= 1; 
	SELECT * FROM employee WHERE dept_code IN ('D1','D2','D3');
	