// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Protocol.proto

package com.chuhui.marshal.framework.transfer.google;

/**
 * Protobuf type {@code com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage}
 */
public  final class ServiceDefinitionPackage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage)
    ServiceDefinitionPackageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ServiceDefinitionPackage.newBuilder() to construct.
  private ServiceDefinitionPackage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ServiceDefinitionPackage() {
    methodName_ = "";
    requestMethod_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    className_ = "";
    serviceRequestAnnotation_ = "";
    serviceUrl_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ServiceDefinitionPackage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            methodName_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              requestMethod_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000002;
            }
            requestMethod_.add(s);
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            className_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            serviceRequestAnnotation_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            serviceUrl_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000002) != 0)) {
        requestMethod_ = requestMethod_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.chuhui.marshal.framework.transfer.google.Protocol.internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.chuhui.marshal.framework.transfer.google.Protocol.internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage.class, com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage.Builder.class);
  }

  private int bitField0_;
  public static final int METHODNAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object methodName_;
  /**
   * <code>string methodName = 1;</code>
   */
  public java.lang.String getMethodName() {
    java.lang.Object ref = methodName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      methodName_ = s;
      return s;
    }
  }
  /**
   * <code>string methodName = 1;</code>
   */
  public com.google.protobuf.ByteString
      getMethodNameBytes() {
    java.lang.Object ref = methodName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      methodName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REQUESTMETHOD_FIELD_NUMBER = 2;
  private com.google.protobuf.LazyStringList requestMethod_;
  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getRequestMethodList() {
    return requestMethod_;
  }
  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  public int getRequestMethodCount() {
    return requestMethod_.size();
  }
  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  public java.lang.String getRequestMethod(int index) {
    return requestMethod_.get(index);
  }
  /**
   * <code>repeated string requestMethod = 2;</code>
   */
  public com.google.protobuf.ByteString
      getRequestMethodBytes(int index) {
    return requestMethod_.getByteString(index);
  }

  public static final int CLASSNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object className_;
  /**
   * <code>string className = 3;</code>
   */
  public java.lang.String getClassName() {
    java.lang.Object ref = className_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      className_ = s;
      return s;
    }
  }
  /**
   * <code>string className = 3;</code>
   */
  public com.google.protobuf.ByteString
      getClassNameBytes() {
    java.lang.Object ref = className_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      className_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SERVICEREQUESTANNOTATION_FIELD_NUMBER = 4;
  private volatile java.lang.Object serviceRequestAnnotation_;
  /**
   * <code>string serviceRequestAnnotation = 4;</code>
   */
  public java.lang.String getServiceRequestAnnotation() {
    java.lang.Object ref = serviceRequestAnnotation_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      serviceRequestAnnotation_ = s;
      return s;
    }
  }
  /**
   * <code>string serviceRequestAnnotation = 4;</code>
   */
  public com.google.protobuf.ByteString
      getServiceRequestAnnotationBytes() {
    java.lang.Object ref = serviceRequestAnnotation_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      serviceRequestAnnotation_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SERVICEURL_FIELD_NUMBER = 5;
  private volatile java.lang.Object serviceUrl_;
  /**
   * <code>string serviceUrl = 5;</code>
   */
  public java.lang.String getServiceUrl() {
    java.lang.Object ref = serviceUrl_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      serviceUrl_ = s;
      return s;
    }
  }
  /**
   * <code>string serviceUrl = 5;</code>
   */
  public com.google.protobuf.ByteString
      getServiceUrlBytes() {
    java.lang.Object ref = serviceUrl_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      serviceUrl_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getMethodNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, methodName_);
    }
    for (int i = 0; i < requestMethod_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, requestMethod_.getRaw(i));
    }
    if (!getClassNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, className_);
    }
    if (!getServiceRequestAnnotationBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, serviceRequestAnnotation_);
    }
    if (!getServiceUrlBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, serviceUrl_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getMethodNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, methodName_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < requestMethod_.size(); i++) {
        dataSize += computeStringSizeNoTag(requestMethod_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getRequestMethodList().size();
    }
    if (!getClassNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, className_);
    }
    if (!getServiceRequestAnnotationBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, serviceRequestAnnotation_);
    }
    if (!getServiceUrlBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, serviceUrl_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage)) {
      return super.equals(obj);
    }
    com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage other = (com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage) obj;

    if (!getMethodName()
        .equals(other.getMethodName())) return false;
    if (!getRequestMethodList()
        .equals(other.getRequestMethodList())) return false;
    if (!getClassName()
        .equals(other.getClassName())) return false;
    if (!getServiceRequestAnnotation()
        .equals(other.getServiceRequestAnnotation())) return false;
    if (!getServiceUrl()
        .equals(other.getServiceUrl())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + METHODNAME_FIELD_NUMBER;
    hash = (53 * hash) + getMethodName().hashCode();
    if (getRequestMethodCount() > 0) {
      hash = (37 * hash) + REQUESTMETHOD_FIELD_NUMBER;
      hash = (53 * hash) + getRequestMethodList().hashCode();
    }
    hash = (37 * hash) + CLASSNAME_FIELD_NUMBER;
    hash = (53 * hash) + getClassName().hashCode();
    hash = (37 * hash) + SERVICEREQUESTANNOTATION_FIELD_NUMBER;
    hash = (53 * hash) + getServiceRequestAnnotation().hashCode();
    hash = (37 * hash) + SERVICEURL_FIELD_NUMBER;
    hash = (53 * hash) + getServiceUrl().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage)
      com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuhui.marshal.framework.transfer.google.Protocol.internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuhui.marshal.framework.transfer.google.Protocol.internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage.class, com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage.Builder.class);
    }

    // Construct using com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      methodName_ = "";

      requestMethod_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000002);
      className_ = "";

      serviceRequestAnnotation_ = "";

      serviceUrl_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.chuhui.marshal.framework.transfer.google.Protocol.internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_descriptor;
    }

    @java.lang.Override
    public com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage getDefaultInstanceForType() {
      return com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage.getDefaultInstance();
    }

    @java.lang.Override
    public com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage build() {
      com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage buildPartial() {
      com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage result = new com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.methodName_ = methodName_;
      if (((bitField0_ & 0x00000002) != 0)) {
        requestMethod_ = requestMethod_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.requestMethod_ = requestMethod_;
      result.className_ = className_;
      result.serviceRequestAnnotation_ = serviceRequestAnnotation_;
      result.serviceUrl_ = serviceUrl_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage) {
        return mergeFrom((com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage other) {
      if (other == com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage.getDefaultInstance()) return this;
      if (!other.getMethodName().isEmpty()) {
        methodName_ = other.methodName_;
        onChanged();
      }
      if (!other.requestMethod_.isEmpty()) {
        if (requestMethod_.isEmpty()) {
          requestMethod_ = other.requestMethod_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureRequestMethodIsMutable();
          requestMethod_.addAll(other.requestMethod_);
        }
        onChanged();
      }
      if (!other.getClassName().isEmpty()) {
        className_ = other.className_;
        onChanged();
      }
      if (!other.getServiceRequestAnnotation().isEmpty()) {
        serviceRequestAnnotation_ = other.serviceRequestAnnotation_;
        onChanged();
      }
      if (!other.getServiceUrl().isEmpty()) {
        serviceUrl_ = other.serviceUrl_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object methodName_ = "";
    /**
     * <code>string methodName = 1;</code>
     */
    public java.lang.String getMethodName() {
      java.lang.Object ref = methodName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        methodName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string methodName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMethodNameBytes() {
      java.lang.Object ref = methodName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        methodName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string methodName = 1;</code>
     */
    public Builder setMethodName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      methodName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string methodName = 1;</code>
     */
    public Builder clearMethodName() {
      
      methodName_ = getDefaultInstance().getMethodName();
      onChanged();
      return this;
    }
    /**
     * <code>string methodName = 1;</code>
     */
    public Builder setMethodNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      methodName_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList requestMethod_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureRequestMethodIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        requestMethod_ = new com.google.protobuf.LazyStringArrayList(requestMethod_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getRequestMethodList() {
      return requestMethod_.getUnmodifiableView();
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public int getRequestMethodCount() {
      return requestMethod_.size();
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public java.lang.String getRequestMethod(int index) {
      return requestMethod_.get(index);
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public com.google.protobuf.ByteString
        getRequestMethodBytes(int index) {
      return requestMethod_.getByteString(index);
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public Builder setRequestMethod(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureRequestMethodIsMutable();
      requestMethod_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public Builder addRequestMethod(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureRequestMethodIsMutable();
      requestMethod_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public Builder addAllRequestMethod(
        java.lang.Iterable<java.lang.String> values) {
      ensureRequestMethodIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, requestMethod_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public Builder clearRequestMethod() {
      requestMethod_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string requestMethod = 2;</code>
     */
    public Builder addRequestMethodBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureRequestMethodIsMutable();
      requestMethod_.add(value);
      onChanged();
      return this;
    }

    private java.lang.Object className_ = "";
    /**
     * <code>string className = 3;</code>
     */
    public java.lang.String getClassName() {
      java.lang.Object ref = className_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        className_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string className = 3;</code>
     */
    public com.google.protobuf.ByteString
        getClassNameBytes() {
      java.lang.Object ref = className_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        className_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string className = 3;</code>
     */
    public Builder setClassName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      className_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string className = 3;</code>
     */
    public Builder clearClassName() {
      
      className_ = getDefaultInstance().getClassName();
      onChanged();
      return this;
    }
    /**
     * <code>string className = 3;</code>
     */
    public Builder setClassNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      className_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object serviceRequestAnnotation_ = "";
    /**
     * <code>string serviceRequestAnnotation = 4;</code>
     */
    public java.lang.String getServiceRequestAnnotation() {
      java.lang.Object ref = serviceRequestAnnotation_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        serviceRequestAnnotation_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string serviceRequestAnnotation = 4;</code>
     */
    public com.google.protobuf.ByteString
        getServiceRequestAnnotationBytes() {
      java.lang.Object ref = serviceRequestAnnotation_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        serviceRequestAnnotation_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string serviceRequestAnnotation = 4;</code>
     */
    public Builder setServiceRequestAnnotation(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      serviceRequestAnnotation_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string serviceRequestAnnotation = 4;</code>
     */
    public Builder clearServiceRequestAnnotation() {
      
      serviceRequestAnnotation_ = getDefaultInstance().getServiceRequestAnnotation();
      onChanged();
      return this;
    }
    /**
     * <code>string serviceRequestAnnotation = 4;</code>
     */
    public Builder setServiceRequestAnnotationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      serviceRequestAnnotation_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object serviceUrl_ = "";
    /**
     * <code>string serviceUrl = 5;</code>
     */
    public java.lang.String getServiceUrl() {
      java.lang.Object ref = serviceUrl_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        serviceUrl_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string serviceUrl = 5;</code>
     */
    public com.google.protobuf.ByteString
        getServiceUrlBytes() {
      java.lang.Object ref = serviceUrl_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        serviceUrl_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string serviceUrl = 5;</code>
     */
    public Builder setServiceUrl(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      serviceUrl_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string serviceUrl = 5;</code>
     */
    public Builder clearServiceUrl() {
      
      serviceUrl_ = getDefaultInstance().getServiceUrl();
      onChanged();
      return this;
    }
    /**
     * <code>string serviceUrl = 5;</code>
     */
    public Builder setServiceUrlBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      serviceUrl_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage)
  }

  // @@protoc_insertion_point(class_scope:com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage)
  private static final com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage();
  }

  public static com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ServiceDefinitionPackage>
      PARSER = new com.google.protobuf.AbstractParser<ServiceDefinitionPackage>() {
    @java.lang.Override
    public ServiceDefinitionPackage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ServiceDefinitionPackage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ServiceDefinitionPackage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ServiceDefinitionPackage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
