package ru.paskal.laba2.entities

import jakarta.persistence.*

@Entity
@Table(name = "prizes", schema = "laba2")
class Prize : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laureate_id")
    var laureate: Laureate? = null

    @Column(name = "year", nullable = false)
    var year: Int? = null

    @Column(name = "category", nullable = false, length = 50)
    var category: String? = null

    @Column(name = "share")
    var share: Int? = null

    @Column(name = "motivation", length = Integer.MAX_VALUE)
    var motivation: String? = null


    @OneToMany(mappedBy = "prize", cascade = [CascadeType.ALL], orphanRemoval = true)
    var affiliations: MutableList<Affiliation> = mutableListOf()
}