package ru.paskal.laba2.entities

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import java.time.LocalDate

@Entity
@Table(name = "laureates")
@NamedEntityGraph(
    name = "laureate-prizes-affiliations",
    attributeNodes = [
        NamedAttributeNode("prizes", subgraph = "prizes-affiliations"),
    ],
    subgraphs = [
        NamedSubgraph(
            name = "prizes-affiliations",
            attributeNodes = [
                NamedAttributeNode("affiliations")
            ]
        )
    ]
)
class Laureate : BaseEntity() {

    @Column(name = "origin_id")
    var originId: Int? = null

    @Column(name = "firstname", nullable = false, length = 100)
    var firstname: String? = null

    @Column(name = "surname", length = 100)
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


//@Transactional(readOnly = true)
//fun Laureate.toDto(): LaureateDto {
//    Hibernate.initialize(this.prizes)
//
//    // Преобразуем список призов в PrizeDto
//    val prizesDto = this@toDto.prizes.map { prize ->
//        Hibernate.initialize(prize.affiliations)
//        val affiliationsDto = prize.affiliations.map { affiliation ->
//            AffiliationDto(
//                name = affiliation.name,
//                city = affiliation.city,
//                country = affiliation.country
//            )
//        }
//        PrizeDto(
//            year = prize.year,
//            category = prize.category,
//            share = prize.share,
//            motivation = prize.motivation,
//            affiliations = affiliationsDto
//        )
//    }
//
//    // Создаём DTO из сущности
//    return LaureateDto(
//        originId = this@toDto.originId,
//        firstname = this@toDto.firstname,
//        surname = this@toDto.surname,
//        born = this@toDto.born,
//        died = this@toDto.died,
//        bornCountry = this@toDto.bornCountry,
//        bornCountryCode = this@toDto.bornCountryCode,
//        bornCity = this@toDto.bornCity,
//        diedCountry = this@toDto.diedCountry,
//        diedCountryCode = this@toDto.diedCountryCode,
//        diedCity = this@toDto.diedCity,
//        gender = this@toDto.gender,
//        prizes = prizesDto
//    )
//}
