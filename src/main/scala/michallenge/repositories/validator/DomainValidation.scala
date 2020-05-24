package michallenge.repositories.validator

trait DomainValidation {
  def errorCode: String
  def errorMessage: String
  lazy val toMap: Map[String, String] =
    Seq("errorCode" -> errorCode, "errorMessage" -> errorMessage).toMap
}

object EmailDoesNotMeetCriteria extends DomainValidation {
  val errorCode: String = DomainValidation.incorrectEmailErrorCode
  val errorMessage: String =
    "Email should be List"
}

object PhoneNumberDoesNotMeetCriteria extends DomainValidation {
  val errorCode: String = DomainValidation.incorrectEmailErrorCode
  val errorMessage: String =
    "Phone number should not be empty"
}

object FirstNameHasSpecialCharacters extends DomainValidation {
  val errorCode: String = DomainValidation.incorrectFirstNameErrorCode
  val errorMessage: String =
    "First name cannot contain spaces, numbers or special characters."
}

object LastNameHasSpecialCharacters extends DomainValidation {
  val errorCode: String = DomainValidation.incorrectLastNameErrorCode
  val errorMessage: String =
    "Last name cannot contain spaces, numbers or special characters."
}

object AgeDoesNotMeetCriteria extends DomainValidation {
  val errorCode: String = DomainValidation.incorrectAgeErrorCode
  val errorMessage: String =
    "Age must not be less than 18 and more than 99."
}

object DomainValidation {
  val incorrectEmailErrorCode = "INCORRECT_EMAIL"
  val incorrectFirstNameErrorCode = "INCORRECT_FIRST_NAME"
  val incorrectLastNameErrorCode = "INCORRECT_LAST_NAME"
  val incorrectAgeErrorCode = "INCORRECT_AGE"

  val allErrorCodes: Seq[String] =
    Seq(
      incorrectEmailErrorCode,
      incorrectFirstNameErrorCode,
      incorrectLastNameErrorCode
    )
}
