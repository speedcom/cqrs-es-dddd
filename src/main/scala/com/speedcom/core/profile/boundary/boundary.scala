package com.speedcom.core.profile.boundary

case class ProfileId(id: Long) extends AnyVal

case class Profile(profileId: ProfileId, name: String, surname: String)