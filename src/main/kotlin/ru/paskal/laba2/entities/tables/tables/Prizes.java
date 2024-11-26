/*
 * This file is generated by jOOQ.
 */
package ru.paskal.laba2.entities.tables.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.paskal.laba2.entities.tables.Keys;
import ru.paskal.laba2.entities.tables.Laba2;
import ru.paskal.laba2.entities.tables.tables.Affiliations.AffiliationsPath;
import ru.paskal.laba2.entities.tables.tables.Laureates.LaureatesPath;
import ru.paskal.laba2.entities.tables.tables.records.PrizesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Prizes extends TableImpl<PrizesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>laba2.prizes</code>
     */
    public static final Prizes PRIZES = new Prizes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PrizesRecord> getRecordType() {
        return PrizesRecord.class;
    }

    /**
     * The column <code>laba2.prizes.id</code>.
     */
    public final TableField<PrizesRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>laba2.prizes.laureate_id</code>.
     */
    public final TableField<PrizesRecord, Integer> LAUREATE_ID = createField(DSL.name("laureate_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>laba2.prizes.year</code>.
     */
    public final TableField<PrizesRecord, Integer> YEAR = createField(DSL.name("year"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>laba2.prizes.category</code>.
     */
    public final TableField<PrizesRecord, String> CATEGORY = createField(DSL.name("category"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>laba2.prizes.share</code>.
     */
    public final TableField<PrizesRecord, Integer> SHARE = createField(DSL.name("share"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>laba2.prizes.motivation</code>.
     */
    public final TableField<PrizesRecord, String> MOTIVATION = createField(DSL.name("motivation"), SQLDataType.CLOB, this, "");

    private Prizes(Name alias, Table<PrizesRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Prizes(Name alias, Table<PrizesRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>laba2.prizes</code> table reference
     */
    public Prizes(String alias) {
        this(DSL.name(alias), PRIZES);
    }

    /**
     * Create an aliased <code>laba2.prizes</code> table reference
     */
    public Prizes(Name alias) {
        this(alias, PRIZES);
    }

    /**
     * Create a <code>laba2.prizes</code> table reference
     */
    public Prizes() {
        this(DSL.name("prizes"), null);
    }

    public <O extends Record> Prizes(Table<O> path, ForeignKey<O, PrizesRecord> childPath, InverseForeignKey<O, PrizesRecord> parentPath) {
        super(path, childPath, parentPath, PRIZES);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class PrizesPath extends Prizes implements Path<PrizesRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> PrizesPath(Table<O> path, ForeignKey<O, PrizesRecord> childPath, InverseForeignKey<O, PrizesRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private PrizesPath(Name alias, Table<PrizesRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public PrizesPath as(String alias) {
            return new PrizesPath(DSL.name(alias), this);
        }

        @Override
        public PrizesPath as(Name alias) {
            return new PrizesPath(alias, this);
        }

        @Override
        public PrizesPath as(Table<?> alias) {
            return new PrizesPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Laba2.LABA2;
    }

    @Override
    public Identity<PrizesRecord, Integer> getIdentity() {
        return (Identity<PrizesRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<PrizesRecord> getPrimaryKey() {
        return Keys.PRIZES_PKEY;
    }

    @Override
    public List<ForeignKey<PrizesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.PRIZES__PRIZES_LAUREATE_ID_FKEY);
    }

    private transient LaureatesPath _laureates;

    /**
     * Get the implicit join path to the <code>laba2.laureates</code> table.
     */
    public LaureatesPath laureates() {
        if (_laureates == null)
            _laureates = new LaureatesPath(this, Keys.PRIZES__PRIZES_LAUREATE_ID_FKEY, null);

        return _laureates;
    }

    private transient AffiliationsPath _affiliations;

    /**
     * Get the implicit to-many join path to the <code>laba2.affiliations</code>
     * table
     */
    public AffiliationsPath affiliations() {
        if (_affiliations == null)
            _affiliations = new AffiliationsPath(this, null, Keys.AFFILIATIONS__AFFILIATIONS_PRIZE_ID_FKEY.getInverseKey());

        return _affiliations;
    }

    @Override
    public Prizes as(String alias) {
        return new Prizes(DSL.name(alias), this);
    }

    @Override
    public Prizes as(Name alias) {
        return new Prizes(alias, this);
    }

    @Override
    public Prizes as(Table<?> alias) {
        return new Prizes(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Prizes rename(String name) {
        return new Prizes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Prizes rename(Name name) {
        return new Prizes(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Prizes rename(Table<?> name) {
        return new Prizes(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prizes where(Condition condition) {
        return new Prizes(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prizes where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prizes where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prizes where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prizes where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prizes where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prizes where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prizes where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prizes whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prizes whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}