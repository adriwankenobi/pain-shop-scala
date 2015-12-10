package com.acerete.service

import com.acerete.service.ColorType._

object Customer {
  val N_CUSTOMERS_MIN: Int = 1
  val N_CUSTOMERS_LIMIT: Int = 100
  val N_CUSTOMERS_MAX: Int = 2000
  val MIN_MATCHES_TO_SATISFY: Int = 1
}

class Customer() {

  private var _likes: Map[ColorType, Set[Color]] = Map()
  for (colorType <- ColorType.values) {
    _likes += (colorType -> Set())
  }
  private var _size: Int = 0 // Duplicated for performance

  def addLike(like: Color) {
    var colors: Set[Color] = _likes.apply(like.cType())
    colors += like
    _likes += (like.cType -> colors)
    _size += 1
  }
  
  def likesSize() = _size
  
  def likes(color: Color) = _likes.apply(color.cType).contains(color)
  
  def likesType(cType: ColorType) = !_likes.apply(cType).isEmpty
  
  override def equals(other: Any): Boolean = {
    other match {
      case that: Customer => this.isInstanceOf[Customer] &&
        _likes == that._likes  &&
        _size == that._size 
      case _ => false
    }
  }
  
  override def toString() = "Customer [likes=" + _likes + ", size=" + _size + "]"
}