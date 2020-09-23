package ru.appline.DNS.tests;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.appline.DNS.managers.DriverManager;
import ru.appline.DNS.managers.InitManager;
import ru.appline.DNS.managers.ManagerPages;
import ru.appline.DNS.managers.PropManager;
import ru.appline.DNS.utils.PropConst;


public class Setup {

    protected ManagerPages app = ManagerPages.getManagerPages();

    protected static PropManager propManager = PropManager.getPropManager();

    @BeforeClass
    public static void setup() {
        InitManager.initFramework();
    }

    @Before
    public void start() {
        DriverManager.getDriver().get(propManager.getProperty(PropConst.APP_URL));
    }

    @AfterClass
    public static void tearDown() {
        InitManager.quitFramework();
    }
}
