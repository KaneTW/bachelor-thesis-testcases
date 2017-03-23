class Unit {}

class List {
    Object value;
    List next;
}

interface Base {
    Object interfaceMethod(Object x);
}

class DerivedA implements Base {
    public Object interfaceMethod(Object x) {
        return new Unit();
    }
}

abstract class DerivedB implements Base {
    public Object interfaceMethod(Object x) {
        return new List();
    }
}

class DerivedBA extends DerivedB {
}

class DerivedBAA extends DerivedBA {
    public Object interfaceMethod(Object x) {
        if (x == null) {
            return "DerivedBAA";
        } else {
            return x;
        }
    }
}

class DerivedBB extends DerivedB {
    public Object interfaceMethod(Object x) {
        List orig = new List();
        List xs = orig;
        for (int i = 0; i < 100; i++) {
            if (x != null) {
                xs.value = x;
            } else {
                xs.value = new Unit();
            }

            xs.next = new List();
            xs = xs.next;
        }
        return orig;
    }
}

public class ResultListTest {
	public static void main(String... args) {
		Base base = null;
		List res = new List();
		base = new DerivedA();
		res.value = base.interfaceMethod(null);
		res.next = new List();
		res = res.next;
		base = new DerivedBA();
		res.value = base.interfaceMethod(null);
		res.next = new List();
		res = res.next;
		base = new DerivedBAA();
		res.value = base.interfaceMethod(null);
	}
}
