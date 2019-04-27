package com.caiwei.demo;

import com.caiwei.demo.importtest.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWebApplicationTests {

	@Autowired
	GeneralClass generalClass;

	@Autowired
	ByConfigClass1 byConfigClass1;

	@Autowired
	ByConfigClass2 byConfigClass2;

	@Autowired
	ByImportSelectorClass byImportSelectorClass;

	@Autowired
	ByImportBeanDefinitionRegistrarClass byImportBeanDefinitionRegistrarClass;

	@Test
	public void contextLoads() {

		generalClass.printClassName();
		byConfigClass1.printClassName();
		byConfigClass2.printClassName();
		byImportSelectorClass.printClassName();
		byImportBeanDefinitionRegistrarClass.printClassName();

	}

}
