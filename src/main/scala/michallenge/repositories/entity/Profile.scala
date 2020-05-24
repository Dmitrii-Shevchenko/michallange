package michallenge.repositories.entity

import michallenge.repositories.entity.Profile.{
  UserId,
  Name,
  Age,
  PhoneNumber,
  Email
}

trait Profile {
  def id: UserId
  def firstName: Name
  def lastName: Name
  def age: Age
  def number: Seq[PhoneNumber]
  def email: Seq[Email]
}

object Profile {
  type UserId = String
  type Name = String
  type Age = String
  type Email = String
  type PhoneNumber = String
}
