package com.loomcom.symon.devices;

import java.util.*;

import com.loomcom.symon.exceptions.*;

public class Memory extends Device {

  private boolean readOnly;
  private int[] mem;

  public Memory(int address, int size, boolean readOnly)
    throws MemoryRangeException {
    super(address, size, "RW Memory");
    this.readOnly = readOnly;
    this.mem = new int[size];
    // Initialize all locations to 0xff
    Arrays.fill(this.mem, 0xff);
  }

  public Memory(int address, int size)
    throws MemoryRangeException {
    this(address, size, false);
  }

  public void write(int address, int data) throws MemoryAccessException {
    if (readOnly) {
      throw new MemoryAccessException("Cannot write to read-only memory at address " + address);
    } else {
      this.mem[address] = data;
    }
  }

  public int read(int address) throws MemoryAccessException {
    return this.mem[address];
  }

  public String toString() {
    return "Memory: " + getMemoryRange().toString();
  }

}