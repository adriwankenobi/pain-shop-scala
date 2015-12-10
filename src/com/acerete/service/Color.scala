package com.acerete.service

import com.acerete.service.ColorType._

object Color {
  val N_COLORS_MIN: Int = 1
  val N_COLORS_LIMIT: Int = 10
  val N_COLORS_MAX: Int = 2000
  val BATCHES_OFFERED_PER_COLOR: Int = 1
}

class Color(id: Int, cType: ColorType) {
  
  private var _id: Int = id
  private var _cType: ColorType = cType
  
  def id() = _id
  
  def cType() = _cType
  
  override def equals(other: Any): Boolean = {
    other match {
      case that: Color => this.isInstanceOf[Color] &&
        _id == that._id &&
        _cType == that._cType
      case _ => false
    }
  }
  
  override def toString() = "Color [id=" + _id + ", type=" + _cType  + "]"
}