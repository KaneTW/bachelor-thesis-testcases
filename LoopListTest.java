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

public class LoopListTest {
	public static void main(String... args) {
		List xs = new List();

        for (int i = 0; i < 10; i++) {
            xs.value = i % 2 == 0 ? new DerivedA() : new DerivedBA();
            xs.next = new List();
            xs = xs.next;
        }

		while (xs != null) {
			Object res = ((Base)xs.value).interfaceMethod(null);
			xs = xs.next;
		}
	}
}
