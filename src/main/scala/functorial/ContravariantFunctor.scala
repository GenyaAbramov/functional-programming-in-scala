package functorial

import scala.collection.generic

trait ContravariantFunctor[F[-_]] extends Companion { module => 
  def apply[A,B](a: F[A])(f: B => A): F[B]
  implicit def syntax[A](m: F[A]): ContravariantFunctor.Syntax[F,A] = new ContravariantFunctor.Syntax[F,A] {
    val F = module
    def self = m
  }
}

object ContravariantFunctor {
  trait Syntax[F[-_],-A] extends HasCompanion[ContravariantFunctor[F]] with Proxy {
    def self: F[A] 
    def map[B](f: B => A): F[B] = F(self)(f)
  }
}

