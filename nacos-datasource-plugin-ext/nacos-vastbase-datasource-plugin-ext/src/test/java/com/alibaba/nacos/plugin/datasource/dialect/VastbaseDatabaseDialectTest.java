/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.plugin.datasource.dialect;

import com.alibaba.nacos.plugin.datasource.constants.DatabaseTypeConstant;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VastbaseDatabaseDialectTest {

    private VastbaseDatabaseDialect dialect;

    @Before
    public void setUp() {
        dialect = new VastbaseDatabaseDialect();
    }

    @Test
    public void testGetType() {
        assertEquals(DatabaseTypeConstant.VASTBASE, dialect.getType());
    }

    @Test
    public void testGetFunctionNow() {
        assertEquals("NOW()", dialect.getFunction("NOW()"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFunctionInvalid() {
        dialect.getFunction("INVALID_FUNC()");
    }

    @Test
    public void testGetLimitTopSqlWithMark() {
        assertEquals("SELECT * FROM test LIMIT ? ", dialect.getLimitTopSqlWithMark("SELECT * FROM test"));
    }

    @Test
    public void testGetLimitPageSqlWithMark() {
        assertEquals("SELECT * FROM test  OFFSET ? LIMIT ? ",
                dialect.getLimitPageSqlWithMark("SELECT * FROM test"));
    }

    @Test
    public void testGetLimitPageSql() {
        assertEquals("SELECT * FROM test  OFFSET 20 LIMIT 10",
                dialect.getLimitPageSql("SELECT * FROM test", 3, 10));
    }

    @Test
    public void testGetLimitPageSqlWithOffset() {
        assertEquals("SELECT * FROM test  OFFSET 50 LIMIT 10",
                dialect.getLimitPageSqlWithOffset("SELECT * FROM test", 50, 10));
    }
}
