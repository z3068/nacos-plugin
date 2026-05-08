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

package com.alibaba.nacos.plugin.datasource.impl.oceanbase;

import com.alibaba.nacos.plugin.datasource.constants.DatabaseTypeConstant;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OceanBaseMapperTest {

    @Test
    public void testConfigInfoMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new ConfigInfoMapperByOceanBase().getDataSource());
    }

    @Test
    public void testConfigInfoBetaMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new ConfigInfoBetaMapperByOceanBase().getDataSource());
    }

    @Test
    public void testConfigInfoGrayMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new ConfigInfoGrayMapperByOceanBase().getDataSource());
    }

    @Test
    public void testConfigInfoTagMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new ConfigInfoTagMapperByOceanBase().getDataSource());
    }

    @Test
    public void testConfigTagsRelationMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new ConfigTagsRelationMapperByOceanBase().getDataSource());
    }

    @Test
    public void testHistoryConfigInfoMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new HistoryConfigInfoMapperByOceanBase().getDataSource());
    }

    @Test
    public void testTenantInfoMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new TenantInfoMapperByOceanBase().getDataSource());
    }

    @Test
    public void testTenantCapacityMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new TenantCapacityMapperByOceanBase().getDataSource());
    }

    @Test
    public void testGroupCapacityMapperGetDataSource() {
        assertEquals(DatabaseTypeConstant.OCEANBASE, new GroupCapacityMapperByOceanBase().getDataSource());
    }
}
