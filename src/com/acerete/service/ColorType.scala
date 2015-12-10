package com.acerete.service

import com.acerete.exception.ColorTypeNotFoundException

object ColorType extends Enumeration {
  
  type ColorType = Value
  
  case class ColorTypeVal(_id: Int) extends Val(_id)
  implicit def valueToColorTypeVal(v: Value): ColorTypeVal = v.asInstanceOf[ColorTypeVal]
  
  val GLOSSY = ColorTypeVal(0)
  val MATTE = ColorTypeVal(1)
  
  val UNKNOWN_COLOR_TYPE_ID: String = "Unknown colorType with id: ";
  
  def cheapest(): ColorType.Value = GLOSSY
  
  @throws(classOf[ColorTypeNotFoundException])
  def byId(id: Int): ColorType.Value = {
    for (colorType <- ColorType.values) {
      if (colorType._id == id) {
        return colorType
      }
    }
    throw new ColorTypeNotFoundException(UNKNOWN_COLOR_TYPE_ID + id);
  }
  
}