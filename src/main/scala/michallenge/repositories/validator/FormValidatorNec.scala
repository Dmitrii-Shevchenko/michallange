package michallenge.repositories.validator

import cats.data.ValidatedNec
import cats.data._
import cats.data.Validated._
import cats.implicits._
import michallenge.repositories.entity.Profile._
import michallenge.repositories.entity.UserRequest

sealed trait FormValidatorNec {

  type ValidationResult[A] = ValidatedNec[DomainValidation, A]

  private def validateFirstName(firstName: Name): ValidationResult[Name] =
    if (firstName.matches("^[a-zA-Z]+$")) firstName.validNec
    else FirstNameHasSpecialCharacters.invalidNec

  private def validateLastName(lastName: Name): ValidationResult[Name] =
    if (lastName.matches("^[a-zA-Z]+$")) lastName.validNec
    else LastNameHasSpecialCharacters.invalidNec

  private def validateAge(age: Age): ValidationResult[Age] =
    if (age.toInt >= 18 && age.toInt <= 75) age.validNec
    else AgeDoesNotMeetCriteria.invalidNec

  def validateNumber(
    number: Seq[PhoneNumber]
  ): ValidationResult[Seq[PhoneNumber]] =
    if (number.nonEmpty) number.validNec
    else PhoneNumberDoesNotMeetCriteria.invalidNec

  def validateEmail(email: Seq[Email]): ValidationResult[Seq[Email]] =
    if (email.nonEmpty) email.validNec else EmailDoesNotMeetCriteria.invalidNec

  def validateForm(firstName: Name,
                   lastName: Name,
                   age: Age,
                   number: Seq[PhoneNumber],
                   email: Seq[Email]): ValidationResult[UserRequest] = {
    (
      validateFirstName(firstName),
      validateLastName(lastName),
      validateAge(age),
      validateNumber(number),
      validateEmail(email)
    ).mapN(UserRequest.apply)
  }

}

object FormValidatorNec extends FormValidatorNec
