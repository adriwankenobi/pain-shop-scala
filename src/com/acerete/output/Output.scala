package com.acerete.output

import com.acerete.service.ColorType._

class Output(caseId: Int) {

  private var _caseId: Int = caseId
  private var _colors: List[ColorType] = List()

  def caseId() = _caseId
	
  def addColor(color: ColorType) {
    addColorList(List(color))
  }

  def addColorList(colorList: List[ColorType]) {
    _colors = _colors ::: colorList
  }
  
  def colors(): List[ColorType] = _colors
  
  def hasColors() = !_colors.isEmpty
  
  override def equals(other: Any): Boolean = {
    other match {
      case that: Output => this.isInstanceOf[Output] &&
        _caseId == that._caseId &&
        _colors == that._colors
      case _ => false
    }
  }
  
  override def toString() = "Output [caseId=" + _caseId + ", colors=" + _colors + "]";
}