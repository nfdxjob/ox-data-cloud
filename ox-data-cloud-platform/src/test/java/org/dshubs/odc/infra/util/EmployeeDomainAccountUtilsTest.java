package org.dshubs.odc.infra.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class EmployeeDomainAccountUtilsTest {

    @Test
    public void testGenerateDomainAccount() throws BadHanyuPinyinOutputFormatCombination {
        String domainAccount = EmployeeDomainAccountUtils.generateDomainAccount("王贤");
        log.info("domainAccount:{}", domainAccount);
        Assertions.assertEquals("wangxian", domainAccount);
        domainAccount = EmployeeDomainAccountUtils.generateDomainAccount("罗光北");
        log.info("domainAccount:{}", domainAccount);
        Assertions.assertEquals("luogb", domainAccount);
        domainAccount = EmployeeDomainAccountUtils.generateDomainAccount("曹韬");
        log.info("domainAccount:{}", domainAccount);
        Assertions.assertEquals("caotao", domainAccount);
    }

}