package com.acerete.input

import com.acerete.service.Customer

object Input {
  val N_CASES_MIN: Int = 1
  val N_CASES_LIMIT: Int = 5
  val N_CASES_MAX: Int = 100
}
  
class Input(caseId: Int, nColors: Int) {

  private var _caseId: Int = caseId
  private var _nColors: Int = nColors
  private var _setCustomers: Set[Customer] = Set()
  
  def caseId() = _caseId
  
  def nColors() = _nColors
  
  def addCustomer(customer: Customer) {
    _setCustomers += customer
  }
  
  def customerSet() = _setCustomers
  
  override def equals(other: Any): Boolean = {
    other match {
      case that: Input => this.isInstanceOf[Input] &&
        _caseId == that._caseId &&
        _nColors == that._nColors &&
        _setCustomers == that._setCustomers 
      case _ => false
    }
  }
  
  override def toString() = "Input [caseId=" + _caseId + ", nColors=" + 
		  _nColors + ", setCustomers=" + _setCustomers + "]"
}