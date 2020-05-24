package michallenge.repositories.validator

import cats.data.ValidatedNel
import cats.implicits._
import michallenge.repositories.entity.Profile._
import michallenge.repositories.entity.UserRequest

object FormValidatorNel {

  type ValidationResult[A] = ValidatedNel[DomainValidation, A]

  private def validateFirstName(firstName: Name): ValidationResult[Name] =
    if (firstName.matches("^[a-zA-Z]+$")) firstName.validNel
    else FirstNameHasSpecialCharacters.invalidNel

  private def validateLastName(lastName: Name): ValidationResult[Name] =
    if (lastName.matches("^[a-zA-Z]+$")) lastName.validNel
    else LastNameHasSpecialCharacters.invalidNel

  private def validateAge(age: Age): ValidationResult[Age] = {
    if (age.toInt >= 18 && age.toInt <= 75) age.validNel
    else AgeDoesNotMeetCriteria.invalidNel
  }

  private def validateNumber(
    number: Seq[PhoneNumber]
  ): ValidationResult[Seq[PhoneNumber]] =
    if (number.nonEmpty) number.validNel
    else PhoneNumberDoesNotMeetCriteria.invalidNel

  private def validateEmail(email: Seq[Email]): ValidationResult[Seq[Email]] =
    if (email.isInstanceOf[Seq[_]]) email.validNel
    else EmailDoesNotMeetCriteria.invalidNel

  def validateForm(userRequest: UserRequest): ValidationResult[UserRequest] =
    (
      validateFirstName(userRequest.firstName),
      validateLastName(userRequest.lastName),
      validateAge(userRequest.age),
      validateNumber(userRequest.number),
      validateEmail(userRequest.email)
    ).mapN(UserRequest.apply)
}
