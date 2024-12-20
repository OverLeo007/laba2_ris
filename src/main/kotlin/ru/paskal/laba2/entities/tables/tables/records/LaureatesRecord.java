/*
 * This file is generated by jOOQ.
 */
package ru.paskal.laba2.entities.tables.tables.records;


import java.time.LocalDate;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import ru.paskal.laba2.entities.tables.tables.Laureates;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LaureatesRecord extends UpdatableRecordImpl<LaureatesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>laba2.laureates.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>laba2.laureates.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>laba2.laureates.origin_id</code>.
     */
    public void setOriginId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>laba2.laureates.origin_id</code>.
     */
    public Integer getOriginId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>laba2.laureates.firstname</code>.
     */
    public void setFirstname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>laba2.laureates.firstname</code>.
     */
    public String getFirstname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>laba2.laureates.surname</code>.
     */
    public void setSurname(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>laba2.laureates.surname</code>.
     */
    public String getSurname() {
        return (String) get(3);
    }

    /**
     * Setter for <code>laba2.laureates.born</code>.
     */
    public void setBorn(LocalDate value) {
        set(4, value);
    }

    /**
     * Getter for <code>laba2.laureates.born</code>.
     */
    public LocalDate getBorn() {
        return (LocalDate) get(4);
    }

    /**
     * Setter for <code>laba2.laureates.died</code>.
     */
    public void setDied(LocalDate value) {
        set(5, value);
    }

    /**
     * Getter for <code>laba2.laureates.died</code>.
     */
    public LocalDate getDied() {
        return (LocalDate) get(5);
    }

    /**
     * Setter for <code>laba2.laureates.born_country</code>.
     */
    public void setBornCountry(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>laba2.laureates.born_country</code>.
     */
    public String getBornCountry() {
        return (String) get(6);
    }

    /**
     * Setter for <code>laba2.laureates.born_country_code</code>.
     */
    public void setBornCountryCode(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>laba2.laureates.born_country_code</code>.
     */
    public String getBornCountryCode() {
        return (String) get(7);
    }

    /**
     * Setter for <code>laba2.laureates.born_city</code>.
     */
    public void setBornCity(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>laba2.laureates.born_city</code>.
     */
    public String getBornCity() {
        return (String) get(8);
    }

    /**
     * Setter for <code>laba2.laureates.died_country</code>.
     */
    public void setDiedCountry(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>laba2.laureates.died_country</code>.
     */
    public String getDiedCountry() {
        return (String) get(9);
    }

    /**
     * Setter for <code>laba2.laureates.died_country_code</code>.
     */
    public void setDiedCountryCode(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>laba2.laureates.died_country_code</code>.
     */
    public String getDiedCountryCode() {
        return (String) get(10);
    }

    /**
     * Setter for <code>laba2.laureates.died_city</code>.
     */
    public void setDiedCity(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>laba2.laureates.died_city</code>.
     */
    public String getDiedCity() {
        return (String) get(11);
    }

    /**
     * Setter for <code>laba2.laureates.gender</code>.
     */
    public void setGender(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>laba2.laureates.gender</code>.
     */
    public String getGender() {
        return (String) get(12);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LaureatesRecord
     */
    public LaureatesRecord() {
        super(Laureates.LAUREATES);
    }

    /**
     * Create a detached, initialised LaureatesRecord
     */
    public LaureatesRecord(Integer id, Integer originId, String firstname, String surname, LocalDate born, LocalDate died, String bornCountry, String bornCountryCode, String bornCity, String diedCountry, String diedCountryCode, String diedCity, String gender) {
        super(Laureates.LAUREATES);

        setId(id);
        setOriginId(originId);
        setFirstname(firstname);
        setSurname(surname);
        setBorn(born);
        setDied(died);
        setBornCountry(bornCountry);
        setBornCountryCode(bornCountryCode);
        setBornCity(bornCity);
        setDiedCountry(diedCountry);
        setDiedCountryCode(diedCountryCode);
        setDiedCity(diedCity);
        setGender(gender);
        resetChangedOnNotNull();
    }
}
