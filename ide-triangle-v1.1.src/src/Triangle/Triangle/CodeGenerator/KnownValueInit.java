/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.Triangle.CodeGenerator;

import Triangle.CodeGenerator.ObjectAddress;
import Triangle.CodeGenerator.RuntimeEntity;

/**
 *
 * @author torre
 */
public class KnownValueInit extends RuntimeEntity {

  public KnownValueInit () {
    super();
    value = 0;
  }

  public KnownValueInit (int size, int value, int addrSize, int level, int displacement) {
    super(size);
    this.value = value;
    this.addrSize = addrSize;
    address = new ObjectAddress(level, displacement);
  }

  public int value;
  public int addrSize;
  public ObjectAddress address;

}
