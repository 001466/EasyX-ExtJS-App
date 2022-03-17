package org.easy.common;

public interface ApplicationAttribute {
	public static final String AUTHORIZED_ID="Authorization";

	public static enum Code {
		SUCCESS(0), FAIL(1), PER_IP_LIMIT(403), NOT_LOGIN(401), NOT_BROWSER(402), PARAMETER_MISS(400);
		private int value;

		Code(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
