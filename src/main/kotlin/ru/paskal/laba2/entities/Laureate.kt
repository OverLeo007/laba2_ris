package ru.paskal.laba2.entities

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import java.time.LocalDate

@Entity
@Table(name = "laureates")
class Laureate : BaseEntity() {

    @Column(name = "origin_id")
    var originId: Int? = null

    @Column(name = "firstname", nullable = false, length = 100)
    var firstname: String? = null

    @Column(name = "surname", nullable = false, length = 100)
    var surname: String? = null

    @Column(name = "born")
    var born: LocalDate? = null

    @Column(name = "died")
    var died: LocalDate? = null

    @Column(name = "born_country", length = 100)
    var bornCountry: String? = null

    @Column(name = "born_country_code", length = 10)
    var bornCountryCode: String? = null

    @Column(name = "born_city", length = 100)
    var bornCity: String? = null

    @Column(name = "died_country", length = 100)
    var diedCountry: String? = null

    @Column(name = "died_country_code", length = 10)
    var diedCountryCode: String? = null

    @Column(name = "died_city", length = 100)
    var diedCity: String? = null

    @Column(name = "gender", length = 10)
    var gender: String? = null

    @OneToMany(mappedBy = "laureate", cascade = [CascadeType.ALL], orphanRemoval = true)
    var prizes: MutableList<Prize> = mutableListOf()



    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , gender = $gender , diedCity = $diedCity , diedCountryCode = $diedCountryCode , diedCountry = $diedCountry , bornCity = $bornCity , bornCountryCode = $bornCountryCode , bornCountry = $bornCountry , died = $died , born = $born , surname = $surname , firstname = $firstname , originId = $originId )"
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Laureate

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

}