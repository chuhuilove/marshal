// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Protocol.proto

package com.chuhui.marshal.framework.transfer.google;

public final class Protocol {
  private Protocol() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_chuhui_marshal_framework_transfer_google_ConsumerRequestPackage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_chuhui_marshal_framework_transfer_google_ConsumerRequestPackage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_chuhui_marshal_framework_transfer_google_SearchServiceRequestPackage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_chuhui_marshal_framework_transfer_google_SearchServiceRequestPackage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_chuhui_marshal_framework_transfer_google_ProducerRequestPackage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_chuhui_marshal_framework_transfer_google_ProducerRequestPackage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016Protocol.proto\022,com.chuhui.marshal.fra" +
      "mework.transfer.google\"&\n\026ConsumerReques" +
      "tPackage\022\014\n\004name\030\001 \001(\t\"@\n\033SearchServiceR" +
      "equestPackage\022\r\n\005group\030\001 \001(\t\022\022\n\nserviceU" +
      "rl\030\002 \001(\t\"\263\001\n\026ProducerRequestPackage\022\023\n\013s" +
      "erverGroup\030\001 \001(\t\022\023\n\013selfAddress\030\002 \001(\t\022\022\n" +
      "\nserverName\030\003 \001(\t\022[\n\013definitions\030\004 \003(\0132F" +
      ".com.chuhui.marshal.framework.transfer.g" +
      "oogle.ServiceDefinitionPackage\"\216\001\n\030Servi" +
      "ceDefinitionPackage\022\022\n\nmethodName\030\001 \001(\t\022" +
      "\025\n\rrequestMethod\030\002 \003(\t\022\021\n\tclassName\030\003 \001(" +
      "\t\022 \n\030serviceRequestAnnotation\030\004 \001(\t\022\022\n\ns" +
      "erviceUrl\030\005 \001(\tB0\n,com.chuhui.marshal.fr" +
      "amework.transfer.googleP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_chuhui_marshal_framework_transfer_google_ConsumerRequestPackage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_chuhui_marshal_framework_transfer_google_ConsumerRequestPackage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_chuhui_marshal_framework_transfer_google_ConsumerRequestPackage_descriptor,
        new java.lang.String[] { "Name", });
    internal_static_com_chuhui_marshal_framework_transfer_google_SearchServiceRequestPackage_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_chuhui_marshal_framework_transfer_google_SearchServiceRequestPackage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_chuhui_marshal_framework_transfer_google_SearchServiceRequestPackage_descriptor,
        new java.lang.String[] { "Group", "ServiceUrl", });
    internal_static_com_chuhui_marshal_framework_transfer_google_ProducerRequestPackage_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_chuhui_marshal_framework_transfer_google_ProducerRequestPackage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_chuhui_marshal_framework_transfer_google_ProducerRequestPackage_descriptor,
        new java.lang.String[] { "ServerGroup", "SelfAddress", "ServerName", "Definitions", });
    internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_chuhui_marshal_framework_transfer_google_ServiceDefinitionPackage_descriptor,
        new java.lang.String[] { "MethodName", "RequestMethod", "ClassName", "ServiceRequestAnnotation", "ServiceUrl", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
