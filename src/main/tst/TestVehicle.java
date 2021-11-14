import dealerapi.dealerapi.Car;
import dealerapi.dealerapi.Truck;
import dealerapi.dealerapi.Vehicle;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class TestVehicle {

    public static Vehicle[] vehicles;
    public static Vehicle v;
    public static Vehicle m;
    public static Car c;
    public static Truck t;

    /**
     * Declare vehicle array to test printVehicle
     * Declare individual vehicle, car and truck to test setters and getters
     */
    @BeforeClass
    public static void declareVehicles(){

        vehicles = new Vehicle[]{
                new Vehicle("Kia","Optima", 2007, false, 14599, 23),
                new Truck("Ford", "F-150", 2015, true, 35000, 17, true, 2),
                new Car("Chevrolet","Corvette", 2021, true, 59000, 15, true),
                new Truck("Ford", "F-150", 2015, true, 35000, 17, false, 2),
                new Car("Chevrolet","Corvette", 2021, true, 59000, 15, false)
        };

        v = new Vehicle("Chevrolet","Corvette", 2021, false, 59000, 15);
        m = new Vehicle("Chevrolet","Corvette", 2021, false, 59000, 15);
        c = new Car("Kia","Optima", 2007, false, 14599, 23, false);
        t = new Truck("Ford", "F-150", 2015, false, 35000, 17, false, 2);

    }

    /**
     * Test printVehicle
     */
    @Test
    public void testPrintVehicle(){
        Assert.assertEquals("""
                2007 Kia Optima
                $14599
                23MPG
                """, vehicles[0].toString());
        Assert.assertEquals("""
                2015 Ford F-150
                FWD
                $35000
                17MPG
                Side Step
                Tow up to 2 tons
                """, vehicles[1].toString());
        Assert.assertEquals("""
                2021 Chevrolet Corvette
                FWD
                $59000
                15MPG
                Convertible
                """, vehicles[2].toString());
        Assert.assertEquals("""
                2015 Ford F-150
                FWD
                $35000
                17MPG
                No Side Step
                Tow up to 2 tons
                """, vehicles[3].toString());
        Assert.assertEquals("""
                2021 Chevrolet Corvette
                FWD
                $59000
                15MPG
                """, vehicles[4].toString());
    }

    /**
     * Test make getter
     */
    @Test
    public void testMake(){
        Assert.assertEquals("Chevrolet", v.getMake());
    }

    /**
     * Test model getter
     */
    @Test
    public void testModel(){
        Assert.assertEquals("Corvette", v.getModel());
    }

    /**
     * Test year getter
     */
    @Test
    public void testYear(){
        Assert.assertEquals(2021,  v.getYear());
    }

    /**
     * Test price getter
     */
    @Test
    public void testPrice(){
        Assert.assertEquals(59000, v.getPrice());
    }

    /**
     * Test four wheel getter
     */
    @Test
    public void testIsFourWheel(){
        Assert.assertFalse(v.getIsFourWheel());
    }

    /**
     * Test mpg getter
     */
    @Test
    public void testMpg(){
        Assert.assertEquals(15, v.getMpg());
    }

    /**
     * Test convertible getter
     */
    @Test
    public void testConvertible(){
        Assert.assertFalse(c.isConvertible());
    }

    /**
     * Test side step getter
     */
    @Test
    public void testSideStep(){
        Assert.assertFalse(t.hasSideStep());
    }

    /**
     * Test tow capacity getter
     */
    @Test
    public void testTowCapacity(){
        Assert.assertEquals(2, t.getTowCapacity());
    }

    /**
     * Test make setter
     */
    @Test
    public void testSetMake(){
        m.setMake("Kia");
        Assert.assertEquals("Kia", m.getMake());
    }

    /**
     * Test model setter
     */
    @Test
    public void testSetModel(){
        m.setModel("Sorento");
        Assert.assertEquals("Sorento", m.getModel());
    }

    /**
     * Test year setter
     */
    @Test
    public void testSetYear(){
        m.setYear(2019);
        Assert.assertEquals(2019,  m.getYear());
    }


    /**
     * Test four wheel setter
     */
    @Test
    public void testSetFourWheel() {
        m.setIsFourWheel(true);
        Assert.assertTrue(m.getIsFourWheel());
    }

    /**
     * Test price setter
     */
    @Test
    public void testSetPrice(){
        m.setPrice(27000);
        Assert.assertEquals(27000, m.getPrice());
    }

    /**
     * Test mpg setter
     */
    @Test
    public void testSetMpg(){
        m.setMpg(22);
        Assert.assertEquals(22, m.getMpg());
    }

    /**
     * Test convertible setter
     */
    @Test
    public void testSetConvertible(){
        c.setConvertible(true);
        Assert.assertTrue(c.isConvertible());
    }

    /**
     * Test side step setter
     */
    @Test
    public void testSetSideStep(){
        t.setHasSideStep(true);
        Assert.assertTrue(t.hasSideStep());
    }

    /**
     * Test tow capacity setterS
     */
    @Test
    public void testSetTowCapacity(){
        t.setTowCapacity(4);
        Assert.assertEquals(4, t.getTowCapacity());
    }


}
