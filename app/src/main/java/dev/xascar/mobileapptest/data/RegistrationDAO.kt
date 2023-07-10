package dev.xascar.mobileapptest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.xascar.mobileapptest.domain.RegistrationFormDomain

@Dao
interface RegistrationDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRegistrationFields(matches: List<RegistrationFormDomain>)

    @Query("Select * from registrationFormDomain order by `order`")
    suspend fun getAllRegistrationFields(): List<RegistrationFormDomain>

    @Query(value = "Delete from registrationFormDomain")
    suspend fun clearRegistrationFields()

}