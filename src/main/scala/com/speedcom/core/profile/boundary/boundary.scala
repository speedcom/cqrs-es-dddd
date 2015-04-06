package com.speedcom.core.profile.boundary

case class ProfileId(id: Long)
case class Profile(profileId: ProfileId, name: String, surname: String)