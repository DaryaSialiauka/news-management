package by.it_academy.service.validation;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import by.it_academy.util.InputDataNewsValidation;

public class ValidationNewsData {

	private final Map<InputDataNewsValidation, String> validData;

	private ValidationNewsData(ValidBuilder b) {
		this.validData = b.validData;
	}

	public Map<InputDataNewsValidation, String> getValidData() {
		return validData;
	}

	public static class ValidBuilder {

		private Map<InputDataNewsValidation, String> validData = new HashMap<>();

		private final static String TITLE_EMPTY = "Title is empty. ";
		private final static String TITLE_LENGTH = "Title length is out of range. ";
		private final static int MAX_TITLE = 45;
		private final static int MIN_TITLE = 3;

		public ValidBuilder titleValid(String title) {

			if (isEmptyString(title)) {
				validData.put(InputDataNewsValidation.TITLE_ERROR, TITLE_EMPTY);
				return this;
			}
			if (title.length() < MIN_TITLE || title.length() > MAX_TITLE) {
				validData.put(InputDataNewsValidation.TITLE_ERROR, TITLE_LENGTH);
			}

			return this;
		}

		private final static String BRIEF_EMPTY = "Brief is empty. ";
		private final static String BRIEF_LENGTH = "Brief length is out of range. ";
		private final static int MAX_BRIEF = 100;
		private final static int MIN_BRIEF = 15;

		public ValidBuilder briefValid(String brief) {

			if (isEmptyString(brief)) {
				validData.put(InputDataNewsValidation.BRIEF_ERROR, BRIEF_EMPTY);
				return this;
			}
			if (brief.length() < MIN_BRIEF || brief.length() > MAX_BRIEF) {
				validData.put(InputDataNewsValidation.BRIEF_ERROR, BRIEF_LENGTH);
			}

			return this;
		}

		private final static String CONTENT_EMPTY = "Content is empty. ";
		private final static String CONTENT_LENGTH = "Content length is out of range. ";
		private final static int MAX_CONTENT = 1000;
		private final static int MIN_CONTENT = 20;

		public ValidBuilder contentValid(String content) {

			if (isEmptyString(content)) {
				validData.put(InputDataNewsValidation.CONTENT_ERROR, CONTENT_EMPTY);
				return this;
			}
			if (content.length() < MIN_CONTENT || content.length() > MAX_CONTENT) {
				validData.put(InputDataNewsValidation.CONTENT_ERROR, CONTENT_LENGTH);
			}

			return this;
		}

		public ValidBuilder dateValid(Calendar date) {

			return this;
		}

		public ValidBuilder authorValid(int id_author) {

			return this;
		}

		public ValidationNewsData build() {
			return new ValidationNewsData(this);
		}

		private final static String NULL_STR = null;

		private static boolean isEmptyString(String str) {
			if (str.isEmpty() || str == NULL_STR) {
				return true;
			}
			return false;
		}

	}
}
