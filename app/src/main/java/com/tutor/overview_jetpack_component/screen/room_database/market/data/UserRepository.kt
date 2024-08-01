package com.tutor.overview_jetpack_component.screen.room_database.market.data

class UserRepository(
	private val dao: UserDao,
) {
	val getName = dao.allUserName()
	val getAge = dao.allUserAge()
	fun getUser() = dao.allUser()
	fun getId(id: Int) = dao.getUserById(id)

	suspend fun insert(user: User) {
		dao.create(user)
	}

	suspend fun delete(user: User) {
		dao.delete(user)
	}

	suspend fun update(user: User) {
		dao.update(user)
	}

}