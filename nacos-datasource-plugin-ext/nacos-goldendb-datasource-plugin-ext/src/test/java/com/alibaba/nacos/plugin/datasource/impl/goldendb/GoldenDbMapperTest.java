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

package com.alibaba.nacos.plugin.datasource.impl.goldendb;

import com.alibaba.nacos.plugin.datasource.constants.DatabaseTypeConstant;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoldenDbMapperTest {

    @Test
    public void testConfigInfoMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new ConfigInfoMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testConfigInfoBetaMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new ConfigInfoBetaMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testConfigInfoGrayMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new ConfigInfoGrayMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testConfigInfoTagMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new ConfigInfoTagMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testConfigTagsRelationMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new ConfigTagsRelationMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testHistoryConfigInfoMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new HistoryConfigInfoMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testTenantInfoMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new TenantInfoMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testTenantCapacityMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new TenantCapacityMapperByGoldenDb().getDataSource());
    }

    @Test
    public void testGroupCapacityMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.GOLDENDB, new GroupCapacityMapperByGoldenDb().getDataSource());
    }
}
