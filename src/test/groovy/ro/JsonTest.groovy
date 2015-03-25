package ro;

import ro.TestCase;

import java.sql.JDBCType;

 class JsonTest extends ro.TestCase {

    class C {
        Object attr;
        Object attr2;

         C() {
            this.attr = "val";
            this.attr2 = "val2";
        }
    }

     void testSerializeAndDeserialize()  {
        C c = (C) Json.load(Json.dump(new C()), C.class);
        Object r = c.attr;
        assertEq("val", r);
    }
}
