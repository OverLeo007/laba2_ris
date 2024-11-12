package ru.paskal.laba2.entities

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "affiliations")
class Affiliation : BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "prize_id")
    var prize: Prize? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "city", length = 100)
    var city: String? = null

    @Column(name = "country", length = 100)
    var country: String? = null
}