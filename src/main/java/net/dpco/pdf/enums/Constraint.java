package net.dpco.pdf.enums;

public enum Constraint {

  NATIONAL_CODE_UNIQUE_CONSTRAINT(Names.UK_APPLICANTS_NATIONAL_CODE_NAME),
  MOBILE_NO_UNIQUE_CONSTRAINT(Names.UK_APPLICANTS_MOBILE_NO_NAME),
  TRACKING_CODE_UNIQUE_CONSTRAINT(Names.UK_APPLICANTS_TRACKING_CODE_NAME),
  ORDER_ID_UNIQUE_CONSTRAINT(Names.UK_PAYMENTS_ORDER_ID),
  PAYMENTS_TRACKING_CODE_UNIQUE_CONSTRAINT(Names.UK_PAYMENTS_TRACKING_CODE),
  NATIONAL_CODE_APPLICANT_STATUS_CONSTRAINT(Names.UK_NATIONAL_CODE_APPLICANT_STATUS_CONSTRAINT),
  ;


  private String name;

  private Constraint(String name) {
    this.name = name;
  }

  public static class Names {

    public static final String UK_APPLICANTS_NATIONAL_CODE_NAME = "UK_APPLICANTS_NATIONAL_CODE";
    public static final String UK_APPLICANTS_MOBILE_NO_NAME = "UK_APPLICANTS_MOBILE_NO";
    public static final String UK_APPLICANTS_TRACKING_CODE_NAME = "UK_APPLICANTS_TRACKING_CODE";
    public static final String UK_PAYMENTS_ORDER_ID = "UK_PAYMENTS_ORDER_ID";
    public static final String UK_PAYMENTS_TRACKING_CODE = "UK_PAYMENTS_TRACKING_CODE";
    public static final String UK_NATIONAL_CODE_APPLICANT_STATUS_CONSTRAINT = "APPT_NATL_CODE_UNQ_CONST";



    private Names() {
      throw new IllegalStateException("Names class");
    }
  }
}
