package functorial

trait MonadPlus[F[+_]] extends Alternative[F] with MonadOr[F] with Filter[F] { module => 
  def guard(cond: Boolean) : F[Unit] = 
    if (cond) unit else empty
  def filter[A](f: F[A], p: A => Boolean): F[A] = 
    f flatMap(a => if (p(a)) pure(a) else empty)
  override implicit def syntax[A](m: F[A]): MonadPlus.Syntax[F,A]
                                      = new MonadPlus.Syntax[F,A] {
    val F = module
    def self = m
  }
}

object MonadPlus {
  trait Syntax[F[+_],+A] extends Alternative.Syntax[F,A]
                            with MonadOr.Syntax[F,A]
                            with Filter.Syntax[F,A]
                            with HasCompanion[MonadPlus[F]]
}
