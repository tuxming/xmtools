package com.xm2013.tools;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class ToolsTest {

	@Test
	public void testIsNotAbsEmpty() {
		assertEquals(true, Tools.isAbsEmpty(""));
		assertEquals(true, Tools.isAbsEmpty(null));
		assertEquals(true, Tools.isAbsEmpty("  "));
		assertEquals(true, Tools.isAbsEmpty("null"));
		
		assertEquals(false, Tools.isAbsEmpty("abss"));
		assertEquals(false, Tools.isAbsEmpty(" s "));
		assertEquals(false, Tools.isAbsEmpty("a   b  "));
		
	}

	@Test
	public void testIsAbsEmpty() {
		assertEquals(true, Tools.isNotAbsEmpty("abss"));
		assertEquals(true, Tools.isNotAbsEmpty(" s "));
		assertEquals(true, Tools.isNotAbsEmpty("a   b  "));
		
		assertEquals(false, Tools.isNotAbsEmpty(""));
		assertEquals(false, Tools.isNotAbsEmpty(null));
		assertEquals(false, Tools.isNotAbsEmpty("  "));
		assertEquals(false, Tools.isNotAbsEmpty("null"));
	}

	@Test
	public void testIsEmpty() {

		assertEquals(true, Tools.isEmpty(null));
		assertEquals(true, Tools.isEmpty(""));
		assertEquals(true, Tools.isEmpty("   "));
		
		assertEquals(false, Tools.isEmpty("adf"));
	}

	@Test
	public void testIsNotEmpty() {
		assertEquals(true, Tools.isNotEmpty("sadf"));
		
		assertEquals(false, Tools.isNotEmpty(""));
		assertEquals(false, Tools.isNotEmpty("   "));
		assertEquals(false, Tools.isNotEmpty(null));
	}

	@Test
	public void testGetTimes() {
		
		Calendar h = Calendar.getInstance();
		Calendar m = Calendar.getInstance();
		Calendar s = Calendar.getInstance();
		
		h.add(Calendar.HOUR_OF_DAY, -1);
		m.add(Calendar.MINUTE, -10);
		s.add(Calendar.SECOND, -10);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		assertEquals("1小时前", Tools.getTimes(df.format(h.getTime())));
		assertEquals("10分钟前", Tools.getTimes(df.format(m.getTime())));
		assertEquals("10秒前", Tools.getTimes(df.format(s.getTime())));
		
	}

	@Test
	public void testCheckEmail() {
		
		assertEquals(true, Tools.checkEmail("tuxming@sina.com"));
		assertEquals(false, Tools.checkEmail("@sina.com"));
		assertEquals(false, Tools.checkEmail("tux.mingsina.com"));

	}

	@Test
	public void testCheckMobileNumber() {
		assertEquals(false, Tools.checkMobileNumber(""));
		assertEquals(true, Tools.checkMobileNumber("13700000000"));
		assertEquals(true, Tools.checkMobileNumber("18600000000"));
		assertEquals(false, Tools.checkMobileNumber("0731-88868885"));
		assertEquals(false, Tools.checkMobileNumber("falfjel"));
		assertEquals(false, Tools.checkMobileNumber("138-0157-5813"));
	}

	@Test
	public void testMonthsDiff() {
		
		Calendar d = Calendar.getInstance();
		Calendar d1 = Calendar.getInstance();
		
		d1.add(Calendar.MONTH, -10);
		
		assertEquals(-10, Tools.monthsDiff(d.getTime(), d1.getTime()));
		assertEquals(10, Tools.monthsDiff(d1.getTime(), d.getTime()));
	}

	@Test
	public void testDaysDiff() {
		Calendar d = Calendar.getInstance();
		Calendar d1 = Calendar.getInstance();
		
		d1.add(Calendar.DAY_OF_YEAR, -11);
		
		assertEquals(10, Tools.daysDiff(d.getTime(), d1.getTime()));
		assertEquals(-10, Tools.daysDiff(d1.getTime(), d.getTime()));
	}


}
