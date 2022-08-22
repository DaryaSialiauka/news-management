package by.it_academy.service.validation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import by.it_academy.bean.News;
import by.it_academy.service.exception.DataNewsValidationException;
import by.it_academy.util.InputDataNewsValidation;

public class NewsDataValidationImpl implements NewsDataValidation {

	private final static String NULL_STR = null;
	private final static int MAX_TITLE = 45;
	private final static int MAX_BRIEF = 100;
	private final static int MAX_CONTENT = 1000;

	@Override
	public boolean newsDataCheck(News news) throws DataNewsValidationException {
		List<InputDataNewsValidation> errorList = new ArrayList<InputDataNewsValidation>();

		if (!checkTitle(news.getTitle())) {
			errorList.add(InputDataNewsValidation.TITLE_ERROR);
		}

		if (!checkBrief(news.getBrief())) {
			errorList.add(InputDataNewsValidation.BRIEF_ERROR);
		}

		if (!checkContent(news.getContent())) {
			errorList.add(InputDataNewsValidation.CONTENT_ERROR);
		}

		if (!checkDate(news.getDate_create())) {
			errorList.add(InputDataNewsValidation.DATE_ERROR);
		}

		if (!errorList.isEmpty()) {
			throw new DataNewsValidationException(errorList, "News not added");
		}

		return true;
	}

	private boolean checkTitle(String title) {
		// TODO
		if (title.length() > MAX_TITLE || title == NULL_STR || title.isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean checkBrief(String brief) {
		// TODO
		if (brief.length() > MAX_BRIEF || brief == NULL_STR || brief.isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean checkContent(String content) {
		// TODO
		if (content.length() > MAX_CONTENT || content == NULL_STR || content.isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean checkDate(Calendar date) {
		// TODO
		return true;
	}

}
