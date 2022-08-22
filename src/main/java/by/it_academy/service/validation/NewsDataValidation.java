package by.it_academy.service.validation;

import by.it_academy.bean.News;
import by.it_academy.service.exception.DataNewsValidationException; 

public interface NewsDataValidation {

	boolean newsDataCheck(News news) throws DataNewsValidationException;
	
}
