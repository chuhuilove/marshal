// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Protocol.proto

package com.chuhui.marshal.framework.transfer.google;

public interface ServiceDefinitionPackageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string methodName = 1;</code>
   */
  java.lang.String getMethodName();
  /**
   * <code>string methodName = 1;</code>
   */
  com.google.protobuf.ByteString
      getMethodNameBytes();

  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  java.util.List<java.lang.String>
      getRequestMethodList();
  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  int getRequestMethodCount();
  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  java.lang.String getRequestMethod(int index);
  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  com.google.protobuf.ByteString
      getRequestMethodBytes(int index);

  /**
   * <code>string className = 3;</code>
   */
  java.lang.String getClassName();
  /**
   * <code>string className = 3;</code>
   */
  com.google.protobuf.ByteString
      getClassNameBytes();

  /**
   * <code>string serviceRequestAnnotation = 4;</code>
   */
  java.lang.String getServiceRequestAnnotation();
  /**
   * <code>string serviceRequestAnnotation = 4;</code>
   */
  com.google.protobuf.ByteString
      getServiceRequestAnnotationBytes();

  /**
   * <code>string serviceUrl = 5;</code>
   */
  java.lang.String getServiceUrl();
  /**
   * <code>string serviceUrl = 5;</code>
   */
  com.google.protobuf.ByteString
      getServiceUrlBytes();

  /**
   * <code>string serviceName = 6;</code>
   */
  java.lang.String getServiceName();
  /**
   * <code>string serviceName = 6;</code>
   */
  com.google.protobuf.ByteString
      getServiceNameBytes();
}
