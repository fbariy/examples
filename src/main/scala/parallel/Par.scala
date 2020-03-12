package parallel

class Par[+A](a: => A) {
  lazy val b: A = a
}

object Par {
  def apply[A](a: => A): Par[A] = new Par(a)

  def unit[A](a: => A): Par[A] = Par(a)
  def get[A](par: Par[A]): A = ???
  def map2[A, B, R](l: Par[A], r: Par[B])(f: (=> A, => B) => R): Par[R] = Par(f(l.b, r.b))
}
