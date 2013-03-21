// Copyright 2012 Cloudera Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.cloudera.impala.analysis;

import com.cloudera.impala.catalog.FileFormat;
import com.cloudera.impala.common.AnalysisException;
import com.cloudera.impala.thrift.TAlterTableParams;
import com.cloudera.impala.thrift.TAlterTableSetFileFormatParams;
import com.cloudera.impala.thrift.TAlterTableType;
import com.cloudera.impala.thrift.TTableName;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

/**
 * Represents an ALTER TABLE SET FILEFORMAT statement.
 */
public class AlterTableSetFileFormatStmt extends AlterTableStmt {
  private final FileFormat fileFormat;

  public AlterTableSetFileFormatStmt(TableName tableName, FileFormat fileFormat) {
    super(tableName); 
    this.fileFormat = fileFormat;
  }

  public FileFormat getFileFormat() {
    return fileFormat;
  }

  @Override
  public TAlterTableParams toThrift() {
    TAlterTableParams params = super.toThrift();
    params.setAlter_type(TAlterTableType.SET_FILE_FORMAT);
    TAlterTableSetFileFormatParams fileFormatParams =
        new TAlterTableSetFileFormatParams(fileFormat.toThrift());
    params.setSet_file_format_params(fileFormatParams);
    return params;
  }
}
